import sys
import pygame

from alien import Alien
from bullet import Bullet
from time import sleep


def check_keydown_events(event,ai_settings,screen,ship,bullets):
    if event.key == pygame.K_RIGHT:
        ship.moving_right = True
    elif event.key == pygame.K_LEFT:
        ship.moving_left = True
    elif event.key == pygame.K_SPACE:
        fire_bullet(ai_settings, screen, ship, bullets)
    elif event.key == pygame.K_q:
        sys.exit()


def check_keyup_events(event,ship):
    if event.key == pygame.K_RIGHT:
        ship.moving_right = False
    elif event.key == pygame.K_LEFT:
        ship.moving_left = False


def check_events(ai_settings, screen, ship,bullets,stats,play_button,aliens,sb):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            check_keydown_events(event,ai_settings,screen,ship,bullets)
        elif event.type == pygame.KEYUP:
            check_keyup_events(event,ship)
        elif event.type == pygame.MOUSEBUTTONDOWN:
            mouse_x, mouse_y = pygame.mouse.get_pos()
            check_play_button(ai_settings,screen, stats,play_button,ship,aliens,bullets,sb,mouse_x,mouse_y)


def check_play_button(ai_settings,screen, stats,play_button,ship,aliens,bullets,sb,mouse_x,mouse_y):
    button_clicked = play_button.rect.collidepoint(mouse_x, mouse_y)
    if button_clicked and not stats.game_active:

        #重置游戏统计信息
        stats.reset_stats()
        stats.game_active = True

        #重置记分牌
        sb.prep_score()
        sb.prep_high_score()
        sb.prep_level()

        #清空外星人群和子弹
        aliens.empty()
        bullets.empty()

        ai_settings.initialize_dynamic_settings()
        pygame.mouse.set_visible(False)
        if play_button.rect.collidepoint(mouse_x, mouse_y):
            if play_button.rect.collidepoint(mouse_x, mouse_y):
                stats.reset_stats()
                stats.game_active = True

                aliens.empty()
                bullets.empty()

                create_fleet(ai_settings, screen, ship, aliens)
                ship.center_ship()


def update_screen(ai_settings, screen, ship,bullets,aliens,play_button,stats,sb):
    screen.fill(ai_settings.bg_color)
    for bullet in bullets.sprites():
        bullet.draw_bullet()
    ship.blitme()
    aliens.draw(screen)
    sb.show_score()
    if not stats.game_active:
        play_button.draw_button()

    pygame.display.flip()


def update_bullets(ai_settings, screen, ship,aliens, bullets,stats,sb):
    bullets.update()

    # 删除已消失子弹
    for bullet in bullets.copy():
        if bullet.rect.bottom <= 0:
            bullets.remove(bullet)

    # 检测子弹与外星人碰撞
    check_bullet_aliens_collision(ai_settings,screen,ship,aliens,bullets,stats,sb)


def check_bullet_aliens_collision(ai_settings, screen,ship,aliens,bullets,stats,sb):
    collisions = pygame.sprite.groupcollide(aliens, bullets, True, True)

    if collisions:
        for aliens in collisions.values():
            stats.score += ai_settings.alien_points * len(aliens)
            stats.level += 1
            sb.prep_score()
        check_high_score(stats, sb)


    if len(aliens) == 0:
        bullets.empty()
        ai_settings.increase_speed()
        create_fleet(ai_settings, screen, ship, aliens)


def fire_bullet(ai_settings, screen, ship, bullets):
    if len(bullets) < ai_settings.bullets_allowed:
        new_bullet = Bullet(ai_settings, screen, ship)
        bullets.add(new_bullet)


# 创建外星人群
def create_fleet(ai_settings, screen,ship,aliens):
    alien = Alien(ai_settings, screen)
    number_aliens_x = get_number_aliens_x(ai_settings, alien.rect.width)
    rows_number = get_number_aliens_y(ai_settings,ship.rect.height,alien.rect.height)
    #创建外星人群
    for row_number in range(rows_number):
        for alien_number in range(number_aliens_x):
            create_alien(ai_settings, screen,aliens,alien_number,row_number)


def get_number_aliens_x(ai_settings, alien_width):
    available_space_x = ai_settings.screen_width - 2 * alien_width
    number_aliens_x = available_space_x // alien_width
    return number_aliens_x - 6


def create_alien(ai_settings, screen,aliens,alien_number,row_number=0):
    alien = Alien(ai_settings, screen)
    alien_width = alien.rect.width
    alien.x = alien_width + 2 * alien_width * alien_number
    alien.rect.x = alien.x
    alien.rect.y = alien.rect.height + 2 * alien.rect.height * row_number
    aliens.add(alien)


def get_number_aliens_y(ai_settings,ship_height,alien_height):
    available_space_y = (ai_settings.screen_height - (3 * alien_height) - ship_height)
    number_rows = available_space_y // (3 * alien_height)
    return number_rows - 2


def update_aliens(aliens):
    aliens.update()


def check_fleet_edges(ai_settings, aliens):
    for alien in aliens.sprites():
        if alien.check_edges():
            change_fleet_direction(ai_settings, aliens)
            break


def change_fleet_direction(ai_settings, aliens):
    for alien in aliens.sprites():
        alien.rect.y += ai_settings.fleet_drop_speed
    ai_settings.fleet_direction *= -1


def ship_hit(ai_settings,stats,screen,ship,aliens,bullets):
    if stats.ships_left > 0:
        stats.ships_left -= 1
        aliens.empty()
        bullets.empty()
        ai_settings.ship_limit -= 1
        create_fleet(ai_settings,screen,ship,aliens)
        ship.center_ship()

        sleep(1)
    else:
        stats.game_active = False
        pygame.mouse.set_visible(True)


def check_aliens_bottom(ai_settings,stats,screen,ship,aliens,bullets):
    screen_rect = screen.get_rect()
    for alien in aliens.sprites():
        if alien.rect.bottom >= screen_rect.bottom:
            ship_hit(ai_settings,stats,screen,ship,aliens,bullets)
            break

def update_aliens(ai_settings,stats,screen,ship, aliens, bullets):
    check_fleet_edges(ai_settings, aliens)
    aliens.update()
    if pygame.sprite.spritecollideany(ship,aliens):
        ship_hit(ai_settings,stats,screen,ship,aliens,bullets)

    check_aliens_bottom(ai_settings,stats,screen,ship,aliens,bullets)


def check_high_score(stats, sb):
    if stats.score > stats.high_score:
        stats.high_score = stats.score
        sb.prep_high_score()

if __name__ == '__main__':
    pass