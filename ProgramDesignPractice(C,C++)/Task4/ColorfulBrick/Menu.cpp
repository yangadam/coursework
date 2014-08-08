#include "stdafx.h"
#include "Menu.h"
#include "ServiceLocator.h"

Menu::MenuResult Menu::show(sf::RenderWindow& window, const char* fileName)
{

	//Load menu image from file
	sf::Texture texture;
	texture.loadFromFile(fileName);
	sf::Sprite sprite(texture);

	//Setup clickable regions

	//Play menu item coordinates
	MenuItem button1;
	button1.rect.top= 114;
	button1.rect.height = 100;
	button1.rect.left = 343;
	button1.rect.width = 350;
	button1.action = Button1;

	//Exit menu item coordinates
	MenuItem button2;
	button2.rect.left = 343;
	button2.rect.width = 350;
	button2.rect.top = 270;
	button2.rect.height = 100;
	button2.action = Button2;

	MenuItem button3;
	button3.rect.top= 403;
	button3.rect.height = 100;
	button3.rect.left = 343;
	button3.rect.width = 350;
	button3.action = Button3;

	MenuItem button4;
	button4.rect.top= 510;
	button4.rect.height = 100;
	button4.rect.left = 343;
	button4.rect.width = 350;
	button4.action = Button4;

	_menuItems.push_back(button1);
	_menuItems.push_back(button2);
	_menuItems.push_back(button3);
	_menuItems.push_back(button4);

	window.clear(sf::Color::White);
	window.draw(sprite);
	if (!strcmp(fileName, "images/settings.png"))
	{
		select(window);
	}
	window.display();

	return getMenuResponse(window);
}

Menu::MenuResult  Menu::getMenuResponse(sf::RenderWindow& window)
{
	sf::Event menuEvent;

	while(true)
	{

		window.pollEvent(menuEvent);
		if(menuEvent.type == sf::Event::MouseButtonPressed)
		{
 			if(ServiceLocator::GetAudio()->IsSongPlaying())
 				ServiceLocator::GetAudio()->StopAllSounds();

			return handleClick(menuEvent.mouseButton.x,menuEvent.mouseButton.y);
		}
		if(menuEvent.type == sf::Event::Closed)
		{
			return Exit;
		}
	}
}

Menu::MenuResult Menu::handleClick(int x, int y)
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

void Menu::select(sf::RenderWindow& rw)
{
	switch(BrickManager::getRate())
	{
	case 16:
		{
			sf::Texture texture;
			texture.loadFromFile("images/golden.png");
			sf::Sprite choice(texture);
			choice.setPosition(720, 135);
			rw.draw(choice);
			break;
		}
	case 14:
		{
			sf::Texture texture;
			texture.loadFromFile("images/lightblue.png");
			sf::Sprite choice(texture);
			choice.setPosition(720, 291);
			rw.draw(choice);
			break;
		}
	case 12:
		{
			sf::Texture texture;
			texture.loadFromFile("images/lightgreen.png");
			sf::Sprite choice(texture);
			choice.setPosition(720, 424);
			rw.draw(choice);
			break;
		}
	}
}