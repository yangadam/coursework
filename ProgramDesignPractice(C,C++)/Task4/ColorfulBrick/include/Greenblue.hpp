#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


class Greenblue:
	public VisibleGameObject
{
public:
	Greenblue();
	~Greenblue();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};