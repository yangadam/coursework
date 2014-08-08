#include "StdAfx.h"
#include "SplashScreen.h"


void SplashScreen::show(sf::RenderWindow& renderWindow)
{
	sf::Texture texture;
	if(!texture.loadFromFile("images/SplashScreen.png"))
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

