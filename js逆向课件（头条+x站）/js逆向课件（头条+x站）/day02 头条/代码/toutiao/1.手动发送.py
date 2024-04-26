import requests

res = requests.get(
    url="https://www.toutiao.com/api/pc/list/feed?channel_id=3189398999&max_behot_time=1656591119&category=pc_profile_channel&client_extra_params=%7B%22short_video_item%22:%22filter%22%7D&aid=24&app_name=toutiao_web&_signature=_02B4Z6wo00901OHcBhAAAIDBH5B6I8q18lTh-AKAAFrQ6hwX3.NVdJKJjrkFYG35kDtWia4qY2wuhCBg1pWUufW3PuxIVPZXY07hqSOs7humF83lDTA-xXbIukNrRS-7OcWlkGV3-BiGpZGB5b"
)
print(res.text)
