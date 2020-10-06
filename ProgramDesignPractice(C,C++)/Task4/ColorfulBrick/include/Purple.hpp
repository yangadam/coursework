#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


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