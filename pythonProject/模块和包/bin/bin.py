
import sys,os
BASE_DIR = os.path.dirname( os.path.dirname( os.path.abspath(__file__) ) )
sys.path.append(BASE_DIR)

from my_module import main

if __name__ == '__main__':
    print(BASE_DIR)
    main.run()
