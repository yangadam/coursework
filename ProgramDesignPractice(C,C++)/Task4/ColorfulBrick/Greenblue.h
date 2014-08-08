#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


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