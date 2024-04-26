
from abc import ABC, abstractmethod


class All_files(ABC):
    @abstractmethod
    def read(self):
        pass

    @abstractmethod
    def write(self):
        pass


class Disk(All_files):
    def read(self):
        print("Reading from disk")

    def write(self):
        print("Writing to disk")


class Cdrom(All_files):
    def read(self):
        print("Reading from cdrom")

    def write(self):
        print("Writing to cdrom")


class Mem(All_files):
    def read(self):
        print("Reading from memory")

    def write(self):
        print("Writing to memory")


m1 = Mem()
m1.read()
m1.write()


封装