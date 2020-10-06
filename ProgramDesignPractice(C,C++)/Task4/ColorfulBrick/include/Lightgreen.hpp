#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


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