#include "stdafx.hpp"
#include "settings.hpp"

#include "stdafx.hpp"
#include "Settings.hpp"


Settings::SettingResult Settings::Show(sf::RenderWindow& window)
{

	//Load menu image from file
	sf::Texture texture;
	texture.loadFromFile("media/images/settings.png");
	sf::Sprite sprite(texture);

	//Setup clickable regions

	//Play menu item coordinates
	MenuItem levelButton1;
	levelButton1.rect.top= 200;
	levelButton1.rect.height = 118;
	levelButton1.rect.left = 130;
	levelButton1.rect.width = 400;
	levelButton1.action = l1;

	//Exit menu item coordinates
	MenuItem levelButton2;
	levelButton2.rect.left = 130;
	levelButton2.rect.width = 400;
	levelButton2.rect.top = 450;
	levelButton2.rect.height = 118;
	levelButton2.action = l2;//�������ж�

	MenuItem levelButton3;
	levelButton3.rect.left = 130;
	levelButton3.rect.width = 400;
	levelButton3.rect.top = 450-118;
	levelButton3.rect.height = 118;
	levelButton3.action = l3;

	MenuItem backButton;
	backButton.rect.left = 130;
	backButton.rect.width = 400;
	backButton.rect.top = 450-118-118;
	backButton.rect.height = 118;
	backButton.action = Back;

	_menuItems.push_back(levelButton1);
	_menuItems.push_back(levelButton2);
	_menuItems.push_back(levelButton3);
	_menuItems.push_back(backButton);

	window.draw(sprite);
	window.display();
	return getMenuResponse(window);
}

Settings::SettingResult Settings::handleClick(int x, int y)
{
	std::list<MenuItem>::iterator it;

	for ( it = _menuItems.begin(); it != _menuItems.end(); it++)
	{
		sf::Rect<int> menuItemRect = (*it).rect;
		if( menuItemRect.top + menuItemRect.height> y 
			&& menuItemRect.top < y 
			&& menuItemRect.left < x 
			&& menuItemRect.left + menuItemRect.width > x)
		{
			return (*it).action;
		}
	}

	return Nothing;
}

Settings::SettingResult  Settings::getMenuResponse(sf::RenderWindow& window)
{
	sf::Event menuEvent;

	while(true)
	{

		window.pollEvent(menuEvent);
		if(menuEvent.type == sf::Event::MouseButtonPressed)
		{
			//if(ServiceLocator::GetAudio()->IsSongPlaying())
			//	ServiceLocator::GetAudio()->StopAllSounds();

			return handleClick(menuEvent.mouseButton.x,menuEvent.mouseButton.y);
		}
		if(menuEvent.type == sf::Event::Closed)
		{
			return Exit;
		}
	}
}