package cn.boommanpro.sxu.common;

/**
 * @author BoomManPro
 * @create 2018/5/2
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target){
        org.springframework.beans.BeanUtils.copyProperties(source,target);
    }
}
