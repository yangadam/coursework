#include "stdafx.hpp"
#include "MainMenu.hpp"


MainMenu::MenuResult MainMenu::Show(sf::RenderWindow& window)
{

	//Load menu image from file
	sf::Texture texture;
	texture.loadFromFile("media/images/mainMenu2.png");
	sf::Sprite sprite(texture);

	//Setup clickable regions

	//Play menu item coordinates
	MenuItem playButton;
	playButton.rect.top= 150;
	playButton.rect.height = 118;
	playButton.rect.left = 130;
	playButton.rect.width = 400;
	playButton.action = Play;

	//Exit menu item coordinates
	MenuItem exitButton;
	exitButton.rect.left = 130;
	exitButton.rect.width = 400;
	exitButton.rect.top = 478;
	exitButton.rect.height = 118;
	exitButton.action = Exit;//�������ж�

	MenuItem helpButton;
	helpButton.rect.left = 130;
	helpButton.rect.width = 400;
	helpButton.rect.top = 478-118;
	helpButton.rect.height = 118;
	helpButton.action = Help;

	MenuItem settingsButton;
	settingsButton.rect.left = 130;
	settingsButton.rect.width = 400;
	settingsButton.rect.top = 478-118-118;
	settingsButton.rect.height = 118;
	settingsButton.action = Settings;

	_menuItems.push_back(playButton);
	_menuItems.push_back(exitButton);
	_menuItems.push_back(helpButton);
	_menuItems.push_back(settingsButton);

	window.draw(sprite);
	window.display();
	return getMenuResponse(window);
}

MainMenu::MenuResult MainMenu::handleClick(int x, int y)
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

MainMenu::MenuResult  MainMenu::getMenuResponse(sf::RenderWindow& window)
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