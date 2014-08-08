#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


class Lightblue:
	public VisibleGameObject
{
public:
	Lightblue();
	~Lightblue();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};