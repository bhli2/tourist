package 循环依赖.构造方法属性循环依赖;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 属性 + 构造方法循环依赖
 * a属性 + b构造
 * b属性 + a构造
 * 两种结果不同
 */
//@AllArgsConstructor
@RestController
public class A2 {

    @Autowired
    private B2 b2;

    @GetMapping("/a/2")
    public String get(){
        return b2.toString();
    }
}
