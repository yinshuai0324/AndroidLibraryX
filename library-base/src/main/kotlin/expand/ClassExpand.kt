package expand

import java.lang.reflect.ParameterizedType

/**
 * @作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-05 16:15
 */

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}
