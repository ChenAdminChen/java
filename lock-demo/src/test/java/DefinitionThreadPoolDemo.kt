import com.chen.pool.DefinitionThreadPool
import org.junit.Test

class DefinitionThreadPoolDemo{

    @Test
    fun definitionThreadPoolTest(){
        var definitionThreadPool = DefinitionThreadPool(3)

        definitionThreadPool.executorThreadPool()
    }
}