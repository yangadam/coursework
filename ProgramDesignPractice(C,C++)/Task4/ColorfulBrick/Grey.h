#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


class Grey:
	public VisibleGameObject
{
public:
	Grey();
	~Grey();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};