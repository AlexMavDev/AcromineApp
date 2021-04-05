package com.alexlopezmalvaez.acronimineapp.ui.view.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexlopezmalvaez.acronimineapp.R
import com.alexlopezmalvaez.acronimineapp.data.api.AcromineClient
import com.alexlopezmalvaez.acronimineapp.data.repository.AcromineRepository
import com.alexlopezmalvaez.acronimineapp.ui.view.adapters.AbbreviationDefinitionsListAdapter
import com.alexlopezmalvaez.acronimineapp.ui.viewmodel.AbbreviationDefinitionsViewModel
import com.alexlopezmalvaez.acronimineapp.ui.viewmodel.AbbreviationDefinitionsViewModelFactory
import kotlinx.android.synthetic.main.fragment_abbreviation_definitions.*


class AbbreviationDefinitionsFragment : Fragment() {

    private lateinit var factory: AbbreviationDefinitionsViewModelFactory
    private lateinit var viewModel: AbbreviationDefinitionsViewModel

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_abbreviation_definitions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = AcromineClient()
        val repository = AcromineRepository(api)
        factory = AbbreviationDefinitionsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(AbbreviationDefinitionsViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.abbreviation_definitions_menu, menu)

        val searchItem = menu?.findItem(R.id.search_abbreviation)
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    searchView.setQuery("", false)
                    searchItem.collapseActionView()
                    Toast.makeText(context, "Looking for $query", Toast.LENGTH_LONG).show()
                    query?.let { searchAbbreviations(it) }

                    return true
                }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    fun searchAbbreviations(query: String)
    {
        viewModel.getAbbreviationDefinitionsList(query)

        viewModel.abbreDefsList.observe(viewLifecycleOwner, Observer { lfAbbreviationItems ->
            if(lfAbbreviationItems.isNotEmpty()){
                rv_abbreviation_definitions.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = AbbreviationDefinitionsListAdapter(lfAbbreviationItems)
                }
            } else {
                Toast.makeText(context, "No records found", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_abbreviation ->
                return false
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}