
def Typed(**kwargs):
    def deco(obj):
        for k, v in kwargs.items():
            setattr(obj, k, v)
        return obj
    return deco



@Typed(x=1,y=2,z=3)
class Foo:
    pass




print(Foo.__dict__)