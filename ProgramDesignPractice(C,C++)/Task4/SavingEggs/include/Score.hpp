#pragma once
#include "stdafx.hpp"

class Score
{
	int score;
public:
	Score();
	void draw(sf::RenderWindow&);
	void reset();
	void operator+= (int); 
};