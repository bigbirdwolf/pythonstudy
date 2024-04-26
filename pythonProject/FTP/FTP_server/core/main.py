import optparse
class ArgvHandler():
    def __init__(self):
        self.parser = optparse.OptionParser()
        self.parser.add_option("-s","--server",dest="server")
        self.parser.add_option("-p", "--port", dest="port")
        # self.parser.add_option("-s", "--server", dest="server")
        # self.parser.add_option("-s", "--server", dest="server")

        options,args = self.parser.parse_args()

        self.verify_args(options,args)

    def verify_args(self,options,args):

        cmd = args[0]




