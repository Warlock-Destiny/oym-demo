@Data
public class ${tableName} {
    <#list columnInfoList as columnInfo>
        /**
        * ${columnInfo.comment!""}
        */
        private ${columnInfo.type} ${columnInfo.columnName};
    </#list>
}