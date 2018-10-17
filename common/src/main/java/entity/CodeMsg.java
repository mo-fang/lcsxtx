package entity;

/**
 * create by Mofang_ysc on 2018/9/13 0013
 */

public class CodeMsg {
    private int retCode;
    private String message;
    // 按照模块定义CodeMsg
    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(200,"success");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(201,"服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(202,"输入参数为空");
    public static CodeMsg DB_EXCEPTION = new CodeMsg(203,"数据异常！");
    // 业务异常
    public static CodeMsg USER_NAMEORPASSWORD_ERROR= new CodeMsg(99100,"账号或用户名密码错误");
    public static CodeMsg SAVE_OR_UPDATE_FAIL= new CodeMsg(99101,"增加或更新记录操作失败！");
    public static CodeMsg DEL_FAIL= new CodeMsg(99102,"删除记录操作失败！");
    public static CodeMsg PIC_FILE_EMPTY= new CodeMsg(99103,"上传文件为空！");
    public static CodeMsg PIC_FILE_FAIL= new CodeMsg(99103,"上传文件失败，请联系管理员！");
    public static CodeMsg HAVE_RELATE_RECORD= new CodeMsg(99104,"有相关联的记录，不能执行删除操作！");
    public static CodeMsg USER_ADDUSER_ERROR= new CodeMsg(99105,"！");

    public static CodeMsg SOLR_ADD_FAIL= new CodeMsg(88100,"搜索引擎索引维护异常（add）！");


    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }
    public int getRetCode() {
        return retCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}