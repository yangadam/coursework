#pragma once
#include "stdafx.h"

class Score
{
	int score;
public:
	Score();
	void draw(sf::RenderWindow&, const sf::Vector2f&, const int);
	void reset();
	void operator+= (int); 
};