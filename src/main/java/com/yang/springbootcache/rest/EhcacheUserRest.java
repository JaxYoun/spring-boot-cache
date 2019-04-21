package com.yang.springbootcache.rest;

import com.yang.springbootcache.model.vo.UserVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * @date: 2019/4/21 22:38
 * @description:
 */
@RestController
@RequestMapping("/ehcache/user")
public class EhcacheUserRest {

    /**
     * 1.使用Ehcache缓存，被缓存的类必须实现序列化接口Serializable
     * 2.第一次使用http://localhost:8080/user/getUserById/1/yangjx请求，返回对象与前台属性一致。
     * 3.第二次请求id不变，更换name，返回结果的name字段仍然为第一次的旧值，表明第一次的对象被缓存了。
     * 4.第三次请求更换id，由于id被缓存用于对象的唯一标识，所以缓存中不存在就会新建一个，然后再缓存，此后相同id的请求都返回那个对象。
     * <p>
     * 6.value (也可使用 cacheNames) : 可看做某类待缓存对象的命名空间【一个对象可以缓存入多个空间】，表示存到哪个缓存空间里。
     * 7.key : 表示命名空间下缓存唯一key,使用Spring Expression Language(简称SpEL,详见参考文献[5])生成。
     * 8.condition : 表示在哪种情况下才缓存结果(对应的还有unless,哪种情况不缓存),同样使用SpEL
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getUserById/{id}/{name}")
    @Cacheable(cacheNames = "ehcachUserCache", key = "#id", condition = "#id < 100")
    public UserVO getUserById(@PathVariable Integer id, @PathVariable String name) {
        System.err.println("getUserById");
        return UserVO.builder().id(id).name(name).password("111111").build();
    }

    /**
     * 当使用http://localhost:8080/user/removeUserById/1删除了缓存的对象后，再调用查询接口，查询接口从缓存拿不到数据，就会象下查找然后再缓存一次。
     *
     * @param id
     * @return
     */
    @GetMapping("/removeUserById/{id}")
    @CacheEvict(value = "ehcachUserCache", key = "#id")
    public boolean removeUserById(@PathVariable Integer id) {
        System.err.println("removeUserById");
        return true;
    }

    /**
     * 请求http://localhost:8080/user/updateUserById/1/kkkkk，将之前的对象的name修改掉，重新查询直接从缓存返回修改后的值，不会触发底层查询
     * 做持久层的更新，同时将更新的内容同步写入缓存，再次以相同id调用查询方法，得到的name将是新值
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/updateUserById/{id}/{name}")
    @CachePut(cacheNames = "ehcachUserCache", key = "#id")
    public UserVO updateUserById(@PathVariable Integer id, @PathVariable String name) {
        UserVO vo = UserVO.builder().id(id).name(name).password("111111").build();
        System.err.println("updateUserById");
        return vo;
    }

}
