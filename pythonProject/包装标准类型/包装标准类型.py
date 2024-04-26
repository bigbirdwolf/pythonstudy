# class List(list):
#     pass
#
#
# l1 = List('hello world')
#
# l2 = list('hello world')
#
# print(l2,type(l2))
# print(l1,type(l1))

#重写append方法
class List(list):
    def append(self, item):

        print('append:', item)

        if(type(item) is str):
            super().append(item)
        else:
            print('item is not string')


l1 = List()
l1.append(123)
l1.append("world")
print(l1)