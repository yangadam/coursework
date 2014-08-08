#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


class Orange:
	public VisibleGameObject
{
public:
	Orange();
	~Orange();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};