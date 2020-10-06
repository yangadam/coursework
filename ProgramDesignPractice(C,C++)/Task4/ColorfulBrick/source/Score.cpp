#include "stdafx.hpp"
#include "Score.hpp"


Score::Score()
{
	score = 0;
}

void Score::draw(sf::RenderWindow& rw, const sf::Vector2f& pos, const int size)
{
	sf::Font font;
	font.loadFromFile("media/fonts/dracula.ttf");
	std::ostrstream str_buf;
	str_buf << "score:   " << score << '\0';
	sf::Text text(str_buf.str(), font, size);
	text.setPosition(pos);
	text.setFillColor(sf::Color::Red);
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