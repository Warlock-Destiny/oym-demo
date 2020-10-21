import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

basePackage = "com.oym."
// 模块包
moduleName = basePackage + ".frame"
// 基础模块包
baseModuleName = basePackage + ".common.base"

// 基础模块内容
baseModuleDao = baseModuleName + ".db.dao.BaseDao"
baseModuleEntity = baseModuleName + ".model.entity"

// 模块内容
moduleDao = moduleName + ".dao"
moduleEntity = moduleName + ".entity"


typeMapping = [
        (~/(?i)int/)                      : "Long",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)timestamp/)                : "java.sql.Timestamp",
        (~/(?i)datetime/)                 : "java.util.Date",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def classComment = table.getComment() ?: ""
    def fields = calcFields(table)
    new File(dir, "dao/" + className + "Dao.java").withPrintWriter("utf-8") { out -> generateDao(out, className, classComment, fields) }
    new File(dir, "entity/" + className + ".java").withPrintWriter("utf-8") { out -> generateEntity(out, table.getName(), className, classComment, fields) }
    new File(dir, "dao/" +  className + ".xml").withPrintWriter("utf-8") { out -> generateMapping(out,table.getName(), className, classComment, fields) }
}
/**
 * 生成mapping文件
 */
def generateMapping(out, tableName, className, classComment, fields) {
    out.println "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"com.oym.activiti.dao.TestZhangydDao\">"

    //batchInsert
//    generateInsert(out, tableName, className, classComment, fields)
    generateBatchInsert(out, tableName, className, classComment, fields)
    out.println "</mapper>"
}

//def generateInsert(out, tableName, className, classComment, fields){
//    //insert
//    out.println "    <insert id=\"insert\" parameterType=\"$moduleEntity.$className\">"
//    out.println "        INSERT INTO $tableName ("
//    for (int i = 0; i < fields.size(); i++) {
//        field = fields.get(i);
//        if (i == (fields.size() - 1)) {
//            out.println "            $field.columnName"
//        }else{
//            out.println "            $field.columnName,"
//        }
//    }
//    out.println "        ) VALUES("
//    for (int i = 0; i < fields.size(); i++) {
//        field = fields.get(i);
//        if (i == (fields.size() - 1)) {
//            out.println "            #{$field.name}"
//        }else{
//            out.println "            #{$field.name},"
//        }
//    }
//    out.println "        )"
//    out.println "    </insert>"
//}

def generateBatchInsert(out, tableName, className, classComment, fields){
    //insert
    out.println "    <insert id=\"batchInsert\" parameterType=\"$moduleEntity.$className\">"
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

/**
 * 生成dao文件
 */
def generateDao(out, className, classComment, fields) {
    out.println "package $moduleDao;"
    out.println ""
    out.println "import $baseModuleDao;"
    out.println "import $moduleEntity"+".$className;"
    out.println "import org.springframework.stereotype.Repository;"
    out.println ""
    out.println "@Repository"
    out.println "public interface $className" + "Dao extends BaseDao<$className> {"
    out.println "}"
}
/**
 * 生成实体类文件
 */
def generateEntity(out,tablename, className, classComment, fields) {
    int count = getExtend(fields)
    out.println "package $moduleEntity;"
    out.println ""
    out.println "import lombok.Data;"
    out.println "import lombok.experimental.Accessors;"
    out.println "import com.baomidou.mybatisplus.annotation.TableField;"
    out.println "import com.baomidou.mybatisplus.annotation.TableName;"
    out.println "import "+getImport(count)
    out.println ""
    out.println "/**"
    out.println " *  $classComment"
    out.println " */"
    out.println "@Data"
    out.println "@Accessors(chain = true)"
    out.println "@TableName(\"$tablename\")"

    out.println "public class $className "+getParent(count) + "{"
    out.println ""
    fields.each() {
        if(count >= 1 && it.columnTop == "ID"){
            return
        }
        if(count >= 2 && (it.columnTop == "ID"||it.columnTop == "GMT_CREATE")){
            return
        }
        if(count >= 3 && (it.columnTop == "ID"||it.columnTop == "GMT_CREATE"||it.columnTop == "GMT_MODIFIED")){
            return
        }
        out.println "    public static final String ${it.columnTop} = \"${it.columnName}\";"
    }
    out.println ""
    fields.each() {
        if(count == 0){

        }
        if(count >= 1 && it.columnTop == "ID"){
            return
        }
        if(count >= 2 && (it.columnTop == "ID"||it.columnTop == "GMT_CREATE")){
            return
        }
        if(count >= 3 && (it.columnTop == "ID"||it.columnTop == "GMT_CREATE"||it.columnTop == "GMT_MODIFIED")){
            return
        }
        if (it.annos != "") out.println "  ${it.annos}"
        out.println "    // ${it.comment}"
        out.println "    @TableField(${it.columnTop})"
        out.println "    private ${it.type} ${it.name};"
    }
    out.println "}"
}

def getExtend(fields){
    int count = 0
    fields.each() {
        def columnName = it.columnName
        if(columnName == "id" || columnName == "gmt_create" || columnName == "gmt_modified"){
            count++
        }
    }
    return count
}

def getImport(count){
    if(count==1){
        return baseModuleEntity+".BaseConnEntity;"
    }else if (count==2){
        return baseModuleEntity+".BaseRecordEntity;"
    }else if (count==3){
        return baseModuleEntity+".BaseCommonEntity;"
    }else{
        return baseModuleEntity+".BaseEntity;"
    }
}

def getParent(count){
    if(count==1){
        return "extends BaseConnEntity"
    }else if (count==2){
        return "extends BaseRecordEntity"
    }else if (count==3){
        return "extends BaseCommonEntity"
    }else{
        return "implements BaseEntity"
    }
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
                           columnTop : col.getName().toUpperCase(),
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
