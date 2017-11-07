#第一个比较系统的springboot的一个测试项目
=
------
涉及的知识有日志的打印、表单的验证、异常的统一处理
-
##日志的打印：涉及AOP、Logger
省略导包

@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.stefan.demo.controller.UserController.*(..))")
    public void log(){
    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURI());
        // /method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
        //
        System.out.println("前aop");
    }
    @After("log()")
    public void doAfter(){
        System.out.println("后aop");
    }
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }
}

#表单的验证
第一步，先在实体类里加验证注释

@Size
@Min
@Max
@Pattern
。。。。

@Table(name="t_user")
@Entity
public class user {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, max = 20, message = "姓名长度必须大于 2 且小于 20 字")
    private String name;
    private String password;
    @Min(value=18,message="年龄必须大于18岁")
    @Max(value=60,message = "年龄必须小于60岁")
    private Integer age;
    @Pattern(regexp = "(男)|(女)",message = "性别只能为男，女")
    private String sex;
    @Pattern(regexp="[0-9]{11}",message = "手机号为11位")
    private String phone;
    #省略getter、setter方法
}

第二步：在UserController里相应方法形参里加@Valid

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
UserService us;
    @GetMapping("/save")
    public Result<user> save(@Valid   user user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(us.addUser(user));
    }
}

#异常统一处理（涉及到新建自定义异常类、管理错误编号、统一格式返回信息）
统一的返回信息样式（save）
成功的：

{
    "code": 0,
    "msg": "成功",
    "data": {
        "id": 17,
        "name": "xxxxx",
        "password": null,
        "age": null,
        "sex": null,
        "phone": null
    }
}

失败的：

{
    "code": 1,
    "msg": "姓名长度必须大于 2 且小于 20 字",
    "data": null
}

统一异常处理具体步骤
#第一步：新建一个统一返回信息的类

public class Result<T> {
    //错误码
    private Integer code;
    //提示信息
    private String msg;
    //具体的内容
    private T data;
    public Result() {
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
#第二步：新建一个统一管理异常编号的枚举类
    
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0, "成功"),
    ERROR(1,""),
    YOUNG(100,"你正处于职业发展期"),
    OLD(101,"你马上就要退休了"),
    ;
    private  Integer code;
    private String msg;
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}

#第三步：新建一个处理返回数据的类（定义成功返回，失败返回方法）

public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    public static Result success() {
        return success(null);
    }
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
    public static  Result error(ResultEnum re){
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOW_ERROR.getCode());
        result.setMsg(ResultEnum.UNKNOW_ERROR.getMsg());
        return result;
    }
}

#第四步:自定义一个异常类（extends RuntimeException）

public class MyException extends RuntimeException {
    private Integer code;
    public MyException(ResultEnum re) {
        super(re.getMsg());
        this.code = re.getCode();
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
#第五步：新建一个处理刚才自定义异常的类

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof MyException){
            MyException myException=(MyException) e;
            return ResultUtil.error(e.getMessage());
        }else{
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);

        }
    }
}

这个springboot项目还是十分简陋的，笔者也在学习当中，关于springboot的实战知识会不断更新，希望能够跟外界有个很好的交流。






