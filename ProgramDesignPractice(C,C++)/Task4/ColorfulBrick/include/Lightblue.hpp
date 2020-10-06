#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


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