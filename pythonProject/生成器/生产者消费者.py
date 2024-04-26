
import time

#生产者
def producer():
    ret = []
    for i in range(10000):
        time.sleep(0.1)
        ret.append('包子%s' %i)
    return ret


#消费者
def consumer(res):
    for (i,v) in enumerate(res):
        time.sleep(0.1)
        print('第%s个人,吃了%s' %(i,v))



res = producer()
consumer(res)


