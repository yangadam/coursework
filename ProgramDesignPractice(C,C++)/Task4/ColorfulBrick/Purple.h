#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


class Purple:
	public VisibleGameObject
{
public:
	Purple();
	~Purple();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};