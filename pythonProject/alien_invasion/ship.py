import pygame

class Ship:

    def __init__(self, screen,ai_settings):
        self.screen = screen
        self.image = pygame.image.load('img/ship.png')
        self.rect = self.image.get_rect()
        self.screen_rect = screen.get_rect()
        self.rect.centerx = self.screen_rect.centerx
        self.rect.bottom = self.screen_rect.bottom
        self.moving_right = False
        self.moving_left = False
        self.ai_settings = ai_settings
        #在飞船属性center中存储小数值
        self.center = float(self.rect.centerx)

    def update(self):
        if self.moving_right:
            if self.rect.centerx < self.screen.get_rect().right - 100:
                self.center += self.ai_settings.ship_speed_factor

        if self.moving_left:
            if self.rect.centerx > 100:
                self.center -= self.ai_settings.ship_speed_factor
        self.rect.centerx = self.center

    def center_ship(self):
        self.center = self.screen_rect.centerx

    def blitme(self):
        self.screen.blit(self.image, self.rect)