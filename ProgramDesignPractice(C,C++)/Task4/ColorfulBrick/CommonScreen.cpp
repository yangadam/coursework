#include "StdAfx.h"
#include "CommonScreen.h"


void CommonScreen::show(sf::RenderWindow& renderWindow, const char* fileName)
{
	sf::Texture texture;
	if(!texture.loadFromFile(fileName))
	{
		return;
	}

	sf::Sprite sprite(texture);
	
	renderWindow.draw(sprite);
	renderWindow.display();

	sf::Event event;
	while(true)
	{
		renderWindow.pollEvent(event);
		if(event.type == sf::Event::KeyPressed 
			|| event.type == sf::Event::MouseButtonPressed
			|| event.type == sf::Event::Closed )
		{
			return;
		}
	}
}