#pragma once
#include "stdafx.h"
#include "BrickManager.h"
#include <list>

class Menu
{

public:
	enum MenuResult { Nothing, Button1, Button2, Button3, Button4, Exit };

	struct MenuItem
	{
	public:
		sf::Rect<int> rect;
		MenuResult action;
	};	
	MenuResult show(sf::RenderWindow&, const char*);

private:
	MenuResult getMenuResponse(sf::RenderWindow&);
	MenuResult handleClick(int, int);
	void select(sf::RenderWindow&);

	std::list<MenuItem> _menuItems;
};

