import sys
import pygame
from pygame.sprite import Group
from button import Button
from scoreboard import Scoreboard
from game_stats import GameStats
from ship import Ship
from settings import Settings
import game_functions as gf

def run_game():
    pygame.init()
    ai_settings = Settings()
    screen = pygame.display.set_mode((ai_settings.screen_height, ai_settings.screen_width))
    pygame.display.set_caption("Alien Invasion")
    play_button = Button(ai_settings, screen, "Play")


    #设置背景色
    bg_color = (230, 230, 230)

    #创建一艘飞船
    ship = Ship(screen, ai_settings)

    #创建一个用于存储子弹的编组
    bullets = Group()

    #创建外星人群
    aliens = Group()

    gf.create_fleet(ai_settings,screen,ship,aliens)

    #创建一个用于存储游戏统计信息的实例
    stats = GameStats(ai_settings)

    #创建记分牌
    sb = Scoreboard(ai_settings, screen, stats)

    while True:
        gf.check_events(ai_settings,screen,ship,bullets,stats,play_button,aliens,sb)
        if stats.game_active:
            ship.update()
            gf.update_bullets(ai_settings,screen,ship,aliens,bullets,stats,sb)
            gf.update_aliens(ai_settings,stats,screen,ship,aliens,bullets)
        gf.update_screen(ai_settings, screen,ship,bullets,aliens,play_button,stats,sb)


if __name__ == "__main__":
    run_game()