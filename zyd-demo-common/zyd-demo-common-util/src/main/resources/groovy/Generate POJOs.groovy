import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "com.cn.zyd.activiti"
baseEntity ="com.cn.zyd.common.db.BaseEntity";
baseDao ="com.cn.zyd.common.db.BaseDao";

typeMapping = [
  (~/(?i)int/)                      : "Long",
  (~/(?i)float|double|decimal|real/): "Double",
  (~/(?i)timestamp/)                : "java.sql.Timestamp",
  (~/(?i)datetime/)                 : "java.sql.Date",
  (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
  SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
  def className = javaName(table.getName(), true)
  def classComment= table.getComment()?:""
  def fields = calcFields(table)
  new File(dir, "dao/"+className + "Dao.java").withPrintWriter { out -> generateDao(out, className, classComment, fields) }
  new File(dir, "entity/"+className + ".java").withPrintWriter { out -> generateEntity(out, className, classComment, fields) }
}

def generateDao(out, className, classComment, fields){
  out.println "package $packageName"+".dao;"
  out.println ""
  out.println "import $baseDao;"
  out.println "import $packageName"+".entity.$className;"
  out.println ""
  out.println "public interface $className"+"Dao extends BaseDao<$className> {"
  out.println "}"
}

def generateEntity(out, className, classComment, fields) {
  out.println "package $packageName"+".entity;"
  out.println ""
  out.println "import $baseEntity;"
  out.println "import lombok.Data;"
  out.println "import lombok.experimental.Accessors;"
  out.println "import lombok.EqualsAndHashCode;"
  out.println ""
  out.println "/**"
  out.println " *  $classComment"
  out.println " */"
  out.println "@Data"
  out.println "@Accessors(fluent = true)"
  out.println "@EqualsAndHashCode(callSuper = true)"
  out.println "public class $className extends BaseEntity {"
  out.println ""
  fields.each() {
    if (it.annos != "") out.println "  ${it.annos}"
    out.println "    // ${it.comment}"
    out.println "    private ${it.type} ${it.name};"
  }
  out.println "}"
}

def calcFields(table) {
  DasUtil.getColumns(table).reduce([]) { fields, col ->
    def spec = Case.LOWER.apply(col.getDataType().getSpecification())
    def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
    fields += [[
                 name : javaName(col.getName(), false),
                 type : typeStr,
                 comment : col.getComment()?:"",
                 annos: ""]]
  }
}

def javaName(str, capitalize) {
  def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
    .collect { Case.LOWER.apply(it).capitalize() }
    .join("")
    .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
  capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
