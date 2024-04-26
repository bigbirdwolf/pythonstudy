#生成器函数
# def test():
#     yield 1
#     yield 2
#     yield 3
#     yield 4
#     return 0
#
# res = test()
# print(res)
# print(res.__next__())
# print(res.__next__())
# print(res.__next__())
# print(res.__next__())

#生产包子
def product_baozi():
    for i in range(100):
        print('正在生产包子')
        yield '一屉包子%s' %i
        print('开始卖包子')

pro_g = product_baozi()

baozi = pro_g.__next__()