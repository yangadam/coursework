#include "stdafx.h"
#include "Score.h"


Score::Score()
{
	score = 0;
}

void Score::draw(sf::RenderWindow& rw)
{
	sf::Font font;
	font.loadFromFile("fonts/JOKERMAN.TTF");
	std::ostrstream str_buf;
	str_buf << "SCORE:" << score << '\0';
	sf::Text text(str_buf.str(), font, 50);
	text.setPosition(0, 300);
	rw.draw(text);
}

void Score::operator+=(int s)
{
	score += s;
}

void Score::reset()
{
	score = 0;
}