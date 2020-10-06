#include "Position.hpp"
#include "stdafx.hpp"

Position::Position()
{
	_pos_i = 0;
	_pos_r = sf::Rect<float>(mostLeft, mostTop, width, height);
	_pos_v = sf::Vector2i(0, 0);

}

Position::Position(int pos_i)
{
	_pos_i = pos_i;
	_pos_r = sf::Rect<float>(mostLeft+pos_i%parallel*width, mostTop+pos_i/parallel*height, width, height);
	_pos_v = sf::Vector2i(pos_i%parallel, pos_i/parallel);
}

Position::Position(sf::Vector2i& pos_v)
{
	_pos_i = pos_v.y*parallel + pos_v.x;
	_pos_r = sf::Rect<float>(mostLeft+pos_v.x*width, mostTop+pos_v.y*height, width, height);
	_pos_v = pos_v;
}

Position::Position(sf::Vector2f& pos_cusor)
{
	_pos_r = sf::Rect<float>((int)pos_cusor.x-((int)pos_cusor.x-mostLeft)%width, (int)pos_cusor.y-((int)pos_cusor.y-mostTop)%height, width, height);
	_pos_v = sf::Vector2i(int(_pos_r.left)/width, int(_pos_r.top-mostTop)/height);
	_pos_i = _pos_v.y*parallel + _pos_v.x;
}

Position::Position(const sf::Rect<float>& pos_r)
{
	_pos_i = (pos_r.left-mostLeft)/width+(pos_r.top-mostTop)/height*parallel;
	_pos_r = pos_r;
	_pos_v = sf::Vector2i(_pos_i%parallel, _pos_i/parallel);
}

void Position::setPosition(int pos_i)
{
	_pos_i = pos_i;
	_pos_r = sf::Rect<float>(mostLeft+pos_i%parallel*width, mostTop+pos_i/parallel*height, width, height);
	_pos_v = sf::Vector2i(pos_i%parallel, pos_i/parallel);
}

void Position::setPosition(sf::Vector2i& pos_v)
{
	_pos_i = pos_v.y*parallel + pos_v.x;
	_pos_r = sf::Rect<float>(mostLeft+pos_v.x*width, mostTop+pos_v.y*height, width, height);
	_pos_v = pos_v;
}

void Position::setPosition(const sf::Rect<float>& pos_r)
{
	_pos_i = (pos_r.left-mostLeft)/width+(pos_r.top-mostTop)/height*parallel;
	_pos_r = pos_r;
	_pos_v = sf::Vector2i(_pos_i%parallel, _pos_i/parallel);
}

const sf::Vector2i& Position::getVector() const
{
	return _pos_v;
}

int Position::getIndex() const
{
	return _pos_i;
}

const sf::Rect<float>& Position::getRect() const
{
	return _pos_r;
}

const sf::Vector2f Position::getTopLeft() const
{
	return sf::Vector2f(_pos_r.left, _pos_r.top);
}

int Position::width = 62;
int Position::height = 58;
int Position::mostTop = 30;
int Position::mostLeft = 0;
int Position::vertical = 12;
int Position::parallel = 16;