#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


class Lightgreen:
	public VisibleGameObject
{
public:
	Lightgreen();
	~Lightgreen();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};