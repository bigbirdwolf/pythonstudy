import requests
from lxml import etree
import json
import re
from bs4 import BeautifulSoup

url = "https://desk.zol.com.cn/pc/"
img_url = "https://desk.zol.com.cn"
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36",
}
response = requests.get(url, headers=headers)
pattern = re.compile(r"var deskPicArr.*?=(?P<imgSrc>.*?);", re.S)
tree = etree.HTML(response.text)
for i in tree.xpath("//ul[@class='pic-list2  clearfix']/li[@class='photo-list-padding']/a[@class='pic']"):
    curl = str(i.xpath("./@href")[0])
    if(curl.find("exe")==-1):
        url = img_url + str(i.xpath("./@href")[0])
        headersReffer = {
            "Referer": url,
        }
        res = requests.get(url, headers=headersReffer)
        tree2 = etree.HTML(res.text)
        # print(tree2.xpath("//script"))
        for item in tree2.xpath("//script"):
            if(item.text != None):
                json_str = json.loads(pattern.search(item.text).group("imgSrc")).get('list')
                for i in json_str:
                    oriSize = i.get("oriSize")
                    imgsrc = i.get("imgsrc")
                    imgsrc = imgsrc.replace("##SIZE##",oriSize)
                    print(oriSize,imgsrc)
                    name = imgsrc.split("/")[-1]
                    resp_img = requests.get(imgsrc, headers=headersReffer)
                    print(resp_img.status_code)
                    with open(f"img/{name}","wb") as f:
                        f.write(resp_img.content)
                break


# print(response.text)