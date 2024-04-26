

class Room:
    tag = "room"
    def __init__(self,owner,name,width,length,height):
        self.owner = owner
        self.name = name
        self.width = width
        self.length = length
        self.height = height

    @property
    def area(self):
        return self.width * self.length

    @classmethod
    def create_room(cls):
        print(cls.tag)

    @staticmethod
    def wash_body(a,b,c):
        print("wash")
