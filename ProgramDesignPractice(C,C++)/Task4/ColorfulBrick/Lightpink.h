#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


class Lightpink:
	public VisibleGameObject
{
public:
	Lightpink();
	~Lightpink();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};