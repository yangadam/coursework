#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


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