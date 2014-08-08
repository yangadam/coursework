#pragma once
#include "stdafx.h"

class Score
{
	int score;
public:
	Score();
	void draw(sf::RenderWindow&);
	void reset();
	void operator+= (int); 
};