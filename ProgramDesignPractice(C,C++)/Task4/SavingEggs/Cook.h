#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"

class Cook:
	public VisibleGameObject
{
public:
	Cook(std::string);
	~Cook();
	void draw(sf::RenderWindow&);
	void update(float);

};