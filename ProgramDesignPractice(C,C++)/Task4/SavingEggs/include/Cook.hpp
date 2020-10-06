#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"

class Cook:
	public VisibleGameObject
{
public:
	Cook(std::string);
	~Cook();
	void draw(sf::RenderWindow&);
	void update(float);

};