import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "com.oym.activiti"
basePath = "com.oym.common.db."
baseEntity = basePath + "BaseEntity";
baseDao = basePath + "BaseDao";


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
    def classComment = table.getComment() ?: ""
    def fields = calcFields(table)
//    new File(dir, "dao/" + className + "Dao.java").withPrintWriter { out -> generateDao(out, className, classComment, fields) }
//    new File(dir, "entity/" + className + ".java").withPrintWriter { out -> generateEntity(out, className, classComment, fields) }
    new File(dir, "mapping/" + className + ".xml").withPrintWriter { out -> generateMapping(out,table.getName(), className, classComment, fields) }
}
/**
 * 生成mapping文件
 */
def generateMapping(out, tableName, className, classComment, fields) {
    out.println "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"com.oym.activiti.dao.TestZhangydDao\">"

    //batchInsert
    generateInsert(out, tableName, className, classComment, fields)
    generateBatchInsert(out, tableName, className, classComment, fields)
    selectById(out, tableName, className, classComment, fields)
    out.println "</mapper>"
}

def generateInsert(out, tableName, className, classComment, fields){
    //insert
    out.println "    <insert id=\"insert\" parameterType=\"$basePath.$className\">"
    out.println "        INSERT INTO $tableName ("
    for (int i = 0; i < fields.size(); i++) {
        field = fields.get(i);
        if (i == (fields.size() - 1)) {
            out.println "            $field.columnName"
        }else{
            out.println "            $field.columnName,"
        }
    }
    out.println "        ) VALUES("
    for (int i = 0; i < fields.size(); i++) {
        field = fields.get(i);
        if (i == (fields.size() - 1)) {
            out.println "            #{$field.name}"
        }else{
            out.println "            #{$field.name},"
        }
    }
    out.println "        )"
    out.println "    </insert>"
}

def generateBatchInsert(out, tableName, className, classComment, fields){
    //insert
    out.println "    <insert id=\"batchInsert\" parameterType=\"$basePath.$className\">"
    out.println "        INSERT INTO $tableName ("
    for (int i = 0; i < fields.size(); i++) {
        field = fields.get(i);
        if (i == (fields.size() - 1)) {
            out.println "            $field.columnName"
        }else{
            out.println "            $field.columnName,"
        }
    }
    out.println "        ) VALUES"
    out.println "        <foreach collection=\"items\" item=\"item\" separator=\",\">"
    out.println "        ("
    for (int i = 0; i < fields.size(); i++) {
        field = fields.get(i);
        if (i == (fields.size() - 1)) {
            out.println "            #{$field.name}"
        }else{
            out.println "            #{$field.name},"
        }
    }
    out.println "        )"
    out.println "        </foreach>"
    out.println "    </insert>"
}

def selectById(out, tableName, className, classComment, fields){
    //insert
    out.println "    <select id=\"selectById\" parameterType=\"java.lang.Long\" resultType=\"$basePath.$className\">"
    out.println "        SELECT "
    for (int i = 0; i < fields.size(); i++) {
        field = fields.get(i);
        if (i == (fields.size() - 1)) {
            out.println "            $field.columnName"
        }else{
            out.println "            $field.columnName,"
        }
    }
    out.println "        FROM $tableName where id =#{id}"
    out.println "    </select>"
}

/**
 * 生成dao文件
 */
def generateDao(out, className, classComment, fields) {
    out.println "package $packageName" + ".dao;"
    out.println ""
    out.println "import $baseDao;"
    out.println "import $packageName" + ".entity.$className;"
    out.println ""
    out.println "public interface $className" + "Dao extends BaseDao<$className> {"
    out.println "}"
}
/**
 * 生成实体类文件
 */
def generateEntity(out, className, classComment, fields) {
    out.println "package $packageName" + ".entity;"
    out.println ""
    out.println "import lombok.Data;"
    out.println "import lombok.experimental.Accessors;"
    out.println "import java.io.Serializable;"
    out.println ""
    out.println "/**"
    out.println " *  $classComment"
    out.println " */"
    out.println "@Data"
    out.println "@Accessors(fluent = true)"
    out.println "public class $className implements Serializable {"
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
                           name      : javaName(col.getName(), false),
                           type      : typeStr,
                           comment   : col.getComment() ?: "",
                           columnName: col.getName(),
                           annos     : ""]]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
