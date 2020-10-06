#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


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