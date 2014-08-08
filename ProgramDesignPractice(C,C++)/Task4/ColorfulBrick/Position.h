#pragma once
#include "stdafx.h"

class Position
{
public:
	Position();
	Position(sf::Vector2i&);
	Position(sf::Vector2f&);
	Position(int);
	Position(const sf::Rect<float>&);
	void setPosition(sf::Vector2i&);
	void setPosition(int);
	void setPosition(const sf::Rect<float>&);
	const sf::Vector2i& getVector() const;
	int getIndex() const;
	const sf::Rect<float>& getRect() const;
	const sf::Vector2f getTopLeft() const;

private:
	int _pos_i;
	sf::Vector2i _pos_v;
	sf::Rect<float> _pos_r;
	static int width;
	static int height;
	static int mostTop;
	static int mostLeft;
	static int parallel;
	static int vertical;
};