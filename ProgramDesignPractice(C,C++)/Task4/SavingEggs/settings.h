//settings需要进一步修改（速度参数）
#pragma once
#include "SFML/Window.hpp"
#include "SFML/Graphics.hpp"
#include <list>

class Settings
{

public:
	enum SettingResult { Nothing, l1, l2, l3, Back, Exit};

	struct MenuItem
	{
	public:
		sf::Rect<int> rect;
		SettingResult action;
	};	
	SettingResult Show(sf::RenderWindow&);

private:
	SettingResult getMenuResponse(sf::RenderWindow&);
	SettingResult handleClick(int, int);

	std::list<MenuItem> _menuItems;
};
