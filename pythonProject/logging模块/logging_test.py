import logging

FORMAT = '%(asctime)s - %(filename)s - [%(lineno)d] - %(message)s'

logging.basicConfig(
    level=logging.DEBUG,
    filename='logging_test.log',
    filemode='w',
    format=FORMAT

)

logging.debug('debug message')
logging.info('info message')
logging.warning('warning message')
logging.error('error message')
logging.critical('critical message')

#使用日志对象的方式

logger = logging.getLogger()

fh = logging.FileHandler('test.log')
ch = logging.StreamHandler()
fm = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s')

logger.addHandler(fh)
logger.addHandler(ch)
fh.setFormatter(fm)
ch.setFormatter(fm)

logger.setLevel('DEBUG')

logger.debug('debug message')
logger.info('info message')
logger.warning('warning message')
logger.error('error message')
logger.critical('critical message')