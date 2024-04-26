from datetime import datetime, timedelta

# 定义班次时间段
shifts = ["A", "B", "C", "D"]

# 根据日期和时间返回对应的班次
def get_shift_at_datetime(target_datetime):
    days_diff = (target_datetime - datetime(2021, 1, 1)).days  # 计算指定日期与起始日期的天数差

    total_hours = days_diff * 24 + target_datetime.hour  # 计算总小时数，包括天数差和当前小时
    shift_index = (total_hours // 8) % 4  # 根据总小时数计算出班次索引
    return shifts[shift_index]

# 测试
target_datetime = datetime(2021, 1, 1, 0, 0)  # 指定日期和时间
current_shift = get_shift_at_datetime(target_datetime)
print(f"The shift at {target_datetime} is: {current_shift}")