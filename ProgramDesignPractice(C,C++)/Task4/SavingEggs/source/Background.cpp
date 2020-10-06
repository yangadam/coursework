#include "stdafx.hpp"
#include "Background.hpp"

void Background::show(sf::RenderWindow& rw, int op1, int op2, int op3)
{
	sf::Texture texture[3];
	if (op1 == 1)
		texture[0].loadFromFile("media/images/1-1.png");
	else
		texture[0].loadFromFile("media/images/1-2.png");

	if (op2 == 1)
		texture[1].loadFromFile("media/images/2-1.png");
	else
		texture[1].loadFromFile("media/images/2-2.png");

	if (op3 == 1)
		texture[2].loadFromFile("media/images/3-1.png");
	else
		texture[2].loadFromFile("media/images/3-2.png");
	
	sf::Sprite sprite[3];
	sprite[0].setTexture(texture[0]);
	sprite[1].setTexture(texture[1]);
	sprite[2].setTexture(texture[2]);
	sprite[0].setPosition(0,0);
	sprite[1].setPosition(166.67,0);
	sprite[2].setPosition(333.34,0);
	
	sf::RectangleShape scoreLine(sf::Vector2f(500, 2));
	scoreLine.setFillColor(sf::Color::Red);
	scoreLine.setPosition(0, 598);
	rw.draw(sprite[0]);
	rw.draw(sprite[1]);
	rw.draw(sprite[2]);
	rw.draw(scoreLine);
}
